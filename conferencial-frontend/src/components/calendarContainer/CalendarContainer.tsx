import React, { useEffect } from 'react';
import type { CalendarProps } from 'antd';
import { Calendar } from 'antd';
import type { Dayjs } from 'dayjs';
import { useAppDispatch, useAppSelector } from '../../store/hooks';
import dayjs from 'dayjs';
import './CalendarContainer.css';
import { setConferenceFormIsOpen,setSelectedDate } from '../../store/userInterfaceActions';



export default function CalendarContainer() {
  const conferences = useAppSelector((state)=> state.conference.conferences);
  const rooms = useAppSelector((state)=> state.room.rooms);
  const dispatch = useAppDispatch();

  const openConferenceForm = (date: Dayjs) => {
    dispatch(setSelectedDate(date));
    dispatch(setConferenceFormIsOpen(true));
  }

  const dateCellRender = (value: Dayjs) => {
    const sortedConferencesForTheDay = conferences.filter((c)=> dayjs(c.startTime).date() === value.date() && dayjs(c.startTime).month() === value.month());

    return (
      <ul className="conferenceCalenderEventList">
        {sortedConferencesForTheDay.map((conference) => (
          <li key={conference.id}>
            <div className='calendarConferenceSelectionDiv'>{conference.name} : {dayjs(conference.startTime).format("HH:mm")} - {dayjs(conference.endTime).format("HH:mm")} - Room: {rooms.find(r=>r.id === conference.roomId)?.name}</div>
            <br/>
          </li>
        ))}
      </ul>
    );
  };

  const cellRender: CalendarProps<Dayjs>['cellRender'] = (current, info) => {
    if (info.type === 'date') return dateCellRender(current);
    return info.originNode;
  };

  return (
    <div className='calendarContainerDiv'>
      <Calendar onSelect={(date)=>openConferenceForm(date)} cellRender={cellRender} showWeek={true}/>
      </div>
  )
}
