/*
 * Copyright (c) 2002-2013, Mairie de Paris
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 *  1. Redistributions of source code must retain the above copyright notice
 *     and the following disclaimer.
 *
 *  2. Redistributions in binary form must reproduce the above copyright notice
 *     and the following disclaimer in the documentation and/or other materials
 *     provided with the distribution.
 *
 *  3. Neither the name of 'Mairie de Paris' nor 'Lutece' nor the names of its
 *     contributors may be used to endorse or promote products derived from
 *     this software without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT HOLDERS OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 *
 * License 1.0
 */
package fr.paris.lutece.plugins.appointment.web;

import fr.paris.lutece.plugins.appointment.business.AppointmentFormHome;
import fr.paris.lutece.plugins.appointment.business.calendar.AppointmentDayHome;
import fr.paris.lutece.plugins.appointment.business.calendar.AppointmentSlot;
import fr.paris.lutece.plugins.appointment.business.calendar.AppointmentSlotHome;
import fr.paris.lutece.portal.service.workflow.WorkflowService;
import fr.paris.lutece.portal.util.mvc.admin.MVCAdminJspBean;
import fr.paris.lutece.portal.util.mvc.admin.annotations.Controller;
import fr.paris.lutece.portal.util.mvc.commons.annotations.Action;
import fr.paris.lutece.portal.util.mvc.commons.annotations.View;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 * JspBean to manage calendar slots
 */
@Controller( controllerJsp = "ManageCalendarSlots.jsp", controllerPath = "jsp/admin/plugins/appointment/", right = AppointmentFormJspBean.RIGHT_MANAGEAPPOINTMENTFORM )
public class CalendarSlotJspBean extends MVCAdminJspBean
{
    private static final long serialVersionUID = 2376721852596997810L;

    private static final String MESSAGE_MANAGE_SLOTS_PAGE_TITLE = "appointment.manageCalendarSlots.pageTitle";

    private static final String PARAMETER_ID_FORM = "id_form";
    private static final String PARAMETER_ID_DAY = "id_day";
    private static final String PARAMETER_ID_SLOT = "id_slot";

    private static final String MARK_LIST_SLOTS = "listSlots";
    private static final String MARK_APPOINTMENTFORM = "appointmentform";
    private static final String MARK_LIST_WORKFLOWS = "listWorkflows";
    private static final String MARK_DAY = "day";

    private static final String VIEW_MANAGE_APPOINTMENT_SLOTS = "manageAppointmentSlots";
    private static final String ACTION_DO_CHANGE_SLOT_ENABLING = "doChangeSlotEnabling";

    private static final String TEMPLATE_MANAGE_FORM_SLOTS = "admin/plugins/appointment/manage_form_slots.html";
    private static final String TEMPLATE_MANAGE_DAY_SLOTS = "admin/plugins/appointment/manage_day_slots.html";

    /**
     * Get the page to manage slots of a form or a day
     * @param request The request
     * @return The HTML content to display or the next URL to redirect to
     */
    @View( VIEW_MANAGE_APPOINTMENT_SLOTS )
    public String getManageSlots( HttpServletRequest request )
    {
        String strIdForm = request.getParameter( PARAMETER_ID_FORM );
        List<AppointmentSlot> listSlots = null;
        String strTemplate;
        Map<String, Object> model = new HashMap<String, Object>( );
        if ( StringUtils.isNotEmpty( strIdForm ) && StringUtils.isNumeric( strIdForm ) )
        {
            int nIdForm = Integer.parseInt( strIdForm );
            listSlots = AppointmentSlotHome.findByIdForm( nIdForm );
            strTemplate = TEMPLATE_MANAGE_FORM_SLOTS;
            model.put( MARK_APPOINTMENTFORM, AppointmentFormHome.findByPrimaryKey( nIdForm ) );
        }
        else
        {
            String strIdDay = request.getParameter( PARAMETER_ID_DAY );
            if ( StringUtils.isNotBlank( strIdDay ) && StringUtils.isNumeric( strIdDay ) )
            {
                int nIdDay = Integer.parseInt( strIdDay );
                listSlots = AppointmentSlotHome.findByIdDay( nIdDay );
                model.put( MARK_DAY, AppointmentDayHome.findByPrimaryKey( nIdDay ) );
            }
            strTemplate = TEMPLATE_MANAGE_DAY_SLOTS;
        }

        if ( listSlots == null )
        {
            return redirect( request, AppointmentFormJspBean.getURLManageAppointmentForms( request ) );
        }

        model.put( MARK_LIST_SLOTS, listSlots );
        model.put( MARK_LIST_WORKFLOWS, WorkflowService.getInstance( ).getWorkflowsEnabled( getUser( ), getLocale( ) ) );
        return getPage( MESSAGE_MANAGE_SLOTS_PAGE_TITLE, strTemplate, model );
    }

    /**
     * Do change the enabling of a slot
     * @param request The request
     * @return The next URL to redirect to
     */
    @Action( ACTION_DO_CHANGE_SLOT_ENABLING )
    public String doChangeSlotEnabling( HttpServletRequest request )
    {
        String strIdSlot = request.getParameter( PARAMETER_ID_SLOT );
        if ( StringUtils.isNotEmpty( strIdSlot ) && StringUtils.isNumeric( strIdSlot ) )
        {
            int nIdSlot = Integer.parseInt( strIdSlot );
            AppointmentSlot slot = AppointmentSlotHome.findByPrimaryKey( nIdSlot );
            slot.setIsEnabled( !slot.getIsEnabled( ) );
            AppointmentSlotHome.update( slot );
            String strIdDay = request.getParameter( PARAMETER_ID_DAY );
            if ( StringUtils.isNotEmpty( strIdDay ) && StringUtils.isNumeric( strIdDay ) )
            {
                return redirect( request, VIEW_MANAGE_APPOINTMENT_SLOTS, PARAMETER_ID_DAY, slot.getIdDay( ) );
            }
            return redirect( request, VIEW_MANAGE_APPOINTMENT_SLOTS, PARAMETER_ID_FORM, slot.getIdForm( ) );
        }
        return redirect( request, AppointmentFormJspBean.getURLManageAppointmentForms( request ) );
    }
}
