import type { CalendarProps } from 'antd';
import { Calendar } from 'antd';
import type { Dayjs } from 'dayjs';
import { useAppDispatch, useConferences } from '../../store/hooks';
import dayjs from 'dayjs';
import './CalendarContainer.css';
import { setIsConferenceEditingModalOpen, setIsConferenceCreationModalOpen,setSelectedDate, setSelectedMonth } from '../../store/userInterfaceActions';
import type { Conference } from '../../util/interfaces';
import { setSelectedConference } from '../../store/conferenceActions';
import type { SelectInfo } from 'antd/es/calendar/generateCalendar';
import type { MouseEvent } from 'react';



export default function CalendarContainer() {
  const conferences = useConferences();
  const dispatch = useAppDispatch();

  const openConferenceForm = (date: Dayjs) => {
    dispatch(setSelectedDate(date));
    dispatch(setIsConferenceCreationModalOpen(true));
  }

  const openEditExistingConferenceForm = (conference: Conference) => {
    dispatch(setSelectedConference(conference));
    dispatch(setIsConferenceEditingModalOpen(true));
  }

  const onPanelChange= (value: Dayjs) => {
    dispatch(setSelectedMonth(value.month()));
  }

  const dateCellRender = (value: Dayjs) => {
    const sortedConferencesForTheDay = conferences.filter((c)=> dayjs(c.startTime).date() === value.date() && dayjs(c.startTime).month() === value.month());

    return (
      <div className="calendarConferenceListContainer">
        <ul className="conferenceCalenderEventList" >
          {sortedConferencesForTheDay.map((conference) => (
            <li key={conference.id} onClick={(event)=> onSelect(null, null, conference, event)} >
              <div className={conference.canceled? 'canceledConferenceElement' : 'conferenceElement'}>{conference.name} : {dayjs(conference.startTime).format("HH:mm")} - {dayjs(conference.endTime).format("HH:mm")}</div>
              <br/>
            </li>
          ))}
        </ul>
      </div>
    );
  };

  const cellRender: CalendarProps<Dayjs>['cellRender'] = (current, info) => {
    if (info.type === 'date') return dateCellRender(current);
    return info.originNode;
  };

  function onSelect(value: Dayjs | null, info: SelectInfo | null, clickedConference: Conference | null, event: MouseEvent | undefined | null){
    if(clickedConference && event){
        event.stopPropagation();
        openEditExistingConferenceForm(clickedConference);
        return;
    }
    if(value && info?.source === "date"){
      // console.log(value.toISOString());
      // console.log(info.source);
      openConferenceForm(value);
    }

  }

  return (
    <div className='calendarContainer'>
      <Calendar 
      className='calendarMain'
      onPanelChange={onPanelChange}
      onSelect={(value, selectInfo)=>onSelect(value, selectInfo, null, null)}
       cellRender={cellRender}
       showWeek={true}/>
      </div>
  )
}
