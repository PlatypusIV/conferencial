import {configureStore} from '@reduxjs/toolkit';
import conferenceReducer from './conferenceActions';
import participantReducer from './participantActions';


const store = configureStore({
    reducer:{
        conference: conferenceReducer,
        participant: participantReducer,
    }
});

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
export default store;