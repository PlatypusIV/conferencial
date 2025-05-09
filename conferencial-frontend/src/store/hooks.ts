import { useDispatch, useSelector } from 'react-redux'
import type { RootState, AppDispatch } from './store'

export const useAppDispatch = useDispatch.withTypes<AppDispatch>()
export const useAppSelector = useSelector.withTypes<RootState>()

export const useConferences = () => useAppSelector((state)=> state.conference.conferences)
export const useParticipants = () => useAppSelector((state)=> state.participant.participants)