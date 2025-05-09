import { useEffect } from 'react'
import './App.css'
import Header from './components/header/Header';
import CalendarContainer from './components/calendarContainer/CalendarContainer';
import { getRequest } from './util/rest';
import { setConferences } from './store/conferenceActions';
import urls from "./util/urls.json";
import { useAppDispatch, useConferences } from './store/hooks';
import dayjs from 'dayjs';


function App() {
  const conferences = useConferences();
  const dispatch = useAppDispatch();

  useEffect(()=>{
    init();
  },[]);

  async function init() {
    const startOfCurrentMonth = dayjs().startOf('month').format('YYYY-MM-DDTHH:mm');
    const endOfCurrentMonth = dayjs().endOf('month').format('YYYY-MM-DDTHH:mm');
    const newConferences = await getRequest(`${urls.conferences}?start=${startOfCurrentMonth}&end=${endOfCurrentMonth}`);
    
    dispatch(setConferences(newConferences));
  }

  return (
    <>
      <Header></Header>
      {conferences.length? <CalendarContainer/> : <div></div>}
    </>
  )
}

export default App
