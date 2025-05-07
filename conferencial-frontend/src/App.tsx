import { useEffect } from 'react'
import './App.css'
import Header from './components/header/Header';
import CalendarContainer from './components/calendarContainer/CalendarContainer';
import { getRequest } from './util/rest';
import urls from './util/urls.json';
import { setConferences } from './store/conferenceActions';

function App() {
  useEffect(()=>{
    init();
  },[]);

  async function init() {
    const conferences = await getRequest(urls.conferences);
    console.log(conferences);
    setConferences(conferences);
  }

  return (
    <>
      <Header></Header>
      <CalendarContainer />
    </>
  )
}

export default App
