import { useState } from 'react'
import './App.css'
import Header from './components/header/Header';
import CalendarContainer from './components/calendarContainer/CalendarContainer';

function App() {
  const [isConferenceFormOpen, setIsConferenceFormOpen] = useState(false);
  const [isParticipantFormOpen, setIsParticipantFormOpen] = useState(false);

  return (
    <>
      <Header></Header>
      <CalendarContainer />
    </>
  )
}

export default App
