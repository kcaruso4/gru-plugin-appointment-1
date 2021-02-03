/*
 * Copyright (c) 2002-2021, City of Paris
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
package fr.paris.lutece.plugins.appointment.business.category;

import java.util.List;

import fr.paris.lutece.plugins.appointment.service.AppointmentPlugin;
import fr.paris.lutece.portal.service.plugin.Plugin;
import fr.paris.lutece.portal.service.plugin.PluginService;
import fr.paris.lutece.portal.service.spring.SpringContextService;

/**
 * This class provides instances management methods for Category objects
 * 
 * @author Laurent Payen
 *
 */
public final class CategoryHome
{

    // Static variable pointed at the DAO instance
    private static ICategoryDAO _dao = SpringContextService.getBean( ICategoryDAO.BEAN_NAME );
    private static Plugin _plugin = PluginService.getPlugin( AppointmentPlugin.PLUGIN_NAME );

    /**
     * Private constructor - this class does not need to be instantiated
     */
    private CategoryHome( )
    {
    }

    /**
     * Create an instance of the Category class
     * 
     * @param category
     *            The instance of the Category which contains the informations to store
     * @return The instance of Category which has been created with its primary key.
     */
    public static Category create( Category category )
    {
        _dao.insert( category, _plugin );

        return category;
    }

    /**
     * Update of the Category which is specified in parameter
     * 
     * @param category
     *            The instance of the Category which contains the data to store
     * @return The instance of the Category which has been updated
     */
    public static Category update( Category category )
    {
        _dao.update( category, _plugin );

        return category;
    }

    /**
     * Delete the Category whose identifier is specified in parameter
     * 
     * @param nKey
     *            The Category Id
     */
    public static void delete( int nKey )
    {
        _dao.delete( nKey, _plugin );
    }

    /**
     * Returns an instance of the Category whose identifier is specified in parameter
     * 
     * @param nKey
     *            The Category primary key
     * @return an instance of the Category
     */
    public static Category findByPrimaryKey( int nKey )
    {
        return _dao.select( nKey, _plugin );
    }

    /**
     * Returns all the Categories parameter
     * 
     * @return a list of all the categories
     */
    public static List<Category> findAllCategories( )
    {
        return _dao.findAllCategories( _plugin );
    }

    /**
     * Find a category by its Label
     * 
     * @param strLabel
     *            the label
     * @return an instance of the category
     */
    public static Category findByLabel( String strLabel )
    {
        return _dao.findByLabel( strLabel, _plugin );
    }

}
