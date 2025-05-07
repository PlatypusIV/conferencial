import React, { useEffect } from 'react'
import type { BadgeProps, CalendarProps } from 'antd'
import { Badge, Calendar } from 'antd'
import type { Dayjs } from 'dayjs'
import { useAppSelector, useAppDispatch } from '../../store/hooks'
import dayjs from 'dayjs'

export default function CalendarContainer() {
  const conferences = useAppSelector((state)=> state.conference.conferences);
  useEffect(()=>{
    console.log(conferences);
  },[conferences]);

  const dateCellRender = (value: Dayjs) => {

    const sortedConferences = conferences.filter((c)=> dayjs(c.startTime).isSame(value, 'day'));
    console.log(sortedConferences);

    return (
      <ul className="events">
        {sortedConferences.map((conference) => (
          <li key={conference.id}>
            <Badge text={conference.name} />
          </li>
        ))}
      </ul>
    );
  };

  // const monthCellRender = (value: Dayjs) => {
  //   const num = getMonthData(value);
  //   return num ? (
  //     <div className="notes-month">
  //       <section>{num}</section>
  //       <span>Backlog number</span>
  //     </div>
  //   ) : null;
  // };

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
