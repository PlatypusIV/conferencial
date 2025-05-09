import {createSlice} from '@reduxjs/toolkit'
import type { Conference } from '../util/interfaces';

interface ConferenceState {
    conferences: Conference[];
}

const initialState: ConferenceState = {
    conferences: []
}

const conferenceSlice = createSlice({
    name:'conference',
    initialState,
    reducers:{
        setConferences: (state: ConferenceState, action) => {
            console.log("action payload: " + action.payload);
            state.conferences = action.payload;
        }
    }
});

export const {setConferences} = conferenceSlice.actions;
export default conferenceSlice.reducer;