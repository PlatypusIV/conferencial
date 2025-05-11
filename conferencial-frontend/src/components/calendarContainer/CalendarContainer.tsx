import React from 'react';
import type { CalendarProps } from 'antd';
import { Button, Calendar } from 'antd';
import type { Dayjs } from 'dayjs';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import dayjs from 'dayjs';
import './CalendarContainer.css';
import { setIsConferenceEditingFormOpen, setIsConferenceFormOpen,setSelectedDate, setSelectedMonth } from '../../store/userInterfaceActions';
import type { Conference } from '../../util/interfaces';
import { setSelectedConference } from '../../store/conferenceActions';



export default function CalendarContainer() {
  const conferences = useAppSelector((state)=> state.conference.conferences);
  const dispatch = useAppDispatch();

  const openConferenceForm = (date: Dayjs) => {
    dispatch(setSelectedDate(date));
    dispatch(setIsConferenceFormOpen(true));
  }

  const openEditExistingConferenceForm = (conference: Conference) => {
    dispatch(setSelectedConference(conference));
    dispatch(setIsConferenceEditingFormOpen(true));
  }

  const onPanelChange= (value: Dayjs) => {
    dispatch(setSelectedMonth(value.month()));
  }

  const dateCellRender = (value: Dayjs) => {

    const sortedConferencesForTheDay = conferences.filter((c)=> dayjs(c.startTime).date() === value.date() && dayjs(c.startTime).month() === value.month());

    return (
      <div className='calendarCellContainer'>
        <Button onClick={()=>openConferenceForm(value)} className='addConferenceButton' type='primary'>+</Button>
      <ul className="conferenceCalenderEventList" >
        {sortedConferencesForTheDay.map((conference) => (
          <li key={conference.id} onClick={()=> openEditExistingConferenceForm(conference)}>
            <div className='calendarConferenceSelectionContainer'>{conference.name} : {dayjs(conference.startTime).format("HH:mm")} - {dayjs(conference.endTime).format("HH:mm")}</div>
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

  return (
    <div className='calendarContainerContainer'>
      {/* onSelect={(date)=>openConferenceForm(date)} */}
      <Calendar 
      className='calendarMain'
      onPanelChange={onPanelChange}
       cellRender={cellRender}
       showWeek={true}/>
      </div>
  )
}
