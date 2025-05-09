import React, { useEffect } from 'react';
import type { CalendarProps } from 'antd';
import { Calendar } from 'antd';
import type { Dayjs } from 'dayjs';
import { useAppSelector } from '../../store/hooks';
import dayjs from 'dayjs';
import './CalendarContainer.css';



export default function CalendarContainer() {
  const conferences = useAppSelector((state)=> state.conference.conferences);
  useEffect(()=>{
    console.log("regular conferences: " + conferences.toString());
  },[conferences]);

  const dateCellRender = (value: Dayjs) => {
    const sortedConferencesForTheDay = conferences.filter((c)=> dayjs(c.startTime).date() === value.date());
    console.log("Sorted conferences: " + sortedConferencesForTheDay.toString());

    return (
      <ul className="conferenceCalenderEventList">
        {sortedConferencesForTheDay.map((conference) => (
          <li key={conference.id}>
            <div className='calendarConferenceSelectionDiv'>{conference.name} : {dayjs(conference.startTime).format("HH:mm")}</div>
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
      <Calendar onSelect={(e)=>console.log(e)} cellRender={cellRender} showWeek={true}/>
      </div>
  )
}
