package fr.paris.lutece.plugins.appointment.service;

import java.time.LocalTime;
import java.util.List;

import fr.paris.lutece.plugins.appointment.business.appointment.Appointment;
import fr.paris.lutece.plugins.appointment.business.form.Form;
import fr.paris.lutece.plugins.appointment.business.planning.TimeSlot;
import fr.paris.lutece.plugins.appointment.business.planning.WeekDefinition;
import fr.paris.lutece.plugins.appointment.business.planning.WorkingDay;
import fr.paris.lutece.plugins.appointment.web.dto.AppointmentFormDTO;
import fr.paris.lutece.test.LuteceTestCase;

public class TimeSlotServiceTest extends LuteceTestCase
{
    public final static String START = "09:00" ;
    public final static String END = "18:00" ;
    private int nIdForm;
    private AppointmentFormDTO appointmentForm;
    /**
     * Find the next time slots of a given time slot
     */
    public void testFindListTimeSlotAfterThisTimeSlot( )
    {

        List<WeekDefinition> listWeekDefinition = WeekDefinitionService.findListWeekDefinition( this.nIdForm );
        List<WorkingDay> listWorkingDay = WorkingDayService.findListWorkingDayByWeekDefinition( listWeekDefinition.get( 0 ).getIdWeekDefinition( ) );
        List<TimeSlot> listTimeSlot = TimeSlotService.findListTimeSlotByWorkingDay( listWorkingDay.get( 0 ).getIdWorkingDay( ) );

        TimeSlot timeSlot = listTimeSlot.stream( ).filter( t -> t.getStartingTime( ).equals( Constants.STARTING_TIME_2 ) ).findFirst( ).get( );

        List<TimeSlot> listNextTimeSlots = TimeSlotService.findListTimeSlotAfterThisTimeSlot( timeSlot );

        assertEquals( 1, listNextTimeSlots.size( ) );
        assertEquals( Constants.ENDING_TIME_2, listNextTimeSlots.get( 0 ).getStartingTime( ) );

    }

    /**
     * Return an ordered and filtered list of time slots after a given time
     */
    public void testGetNextTimeSlotsInAListOfTimeSlotAfterALocalTime( )
    {

        List<WeekDefinition> listWeekDefinition = WeekDefinitionService.findListWeekDefinition( this.nIdForm );
        List<WorkingDay> listWorkingDay = WorkingDayService.findListWorkingDayByWeekDefinition( listWeekDefinition.get( 0 ).getIdWeekDefinition( ) );
        List<TimeSlot> listTimeSlot = TimeSlotService.findListTimeSlotByWorkingDay( listWorkingDay.get( 0 ).getIdWorkingDay( ) );

        List<TimeSlot> listNextTimeSlots = TimeSlotService.getNextTimeSlotsInAListOfTimeSlotAfterALocalTime( listTimeSlot, Constants.TIME_5);

        assertEquals( 1, listNextTimeSlots.size( ) );
    }

    /**
     * Returns the time slot in a list of time slot with the given starting time
     */
    public void testGetTimeSlotInListOfTimeSlotWithStartingTime( )
    {
        List<WeekDefinition> listWeekDefinition = WeekDefinitionService.findListWeekDefinition( this.nIdForm );
        List<WorkingDay> listWorkingDay = WorkingDayService.findListWorkingDayByWeekDefinition( listWeekDefinition.get( 0 ).getIdWeekDefinition( ) );
        List<TimeSlot> listTimeSlot = TimeSlotService.findListTimeSlotByWorkingDay( listWorkingDay.get( 0 ).getIdWorkingDay( ) );

        assertEquals( Constants.ENDING_TIME_2, TimeSlotService.getTimeSlotInListOfTimeSlotWithStartingTime( listTimeSlot, Constants.STARTING_TIME_2 )
                .getEndingTime( ) );

    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();
        this.appointmentForm = FormServiceTest.buildAppointmentForm( );
        this.appointmentForm.setTimeStart( START );
        this.appointmentForm.setTimeEnd( END );
        this.appointmentForm.setDurationAppointments( 30 );
        this.nIdForm = FormService.createAppointmentForm( this.appointmentForm );

    }

    @Override
    protected void tearDown() throws Exception {
        super.tearDown();
        //delete all the forms left over from tests
        for (Form f : FormService.findAllForms()) {
            FormService.removeForm(f.getIdForm());
        }
        this.appointmentForm = null;
        this.nIdForm = 0;
    }

}
