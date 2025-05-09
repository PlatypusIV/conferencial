import {configureStore, createListenerMiddleware} from '@reduxjs/toolkit';
import conferenceReducer from './conferenceActions';
import participantReducer from './participantActions';

const listenerMiddleware = createListenerMiddleware();

const store = configureStore({
    reducer:{
        conference: conferenceReducer,
        participant: participantReducer,
    },
    middleware: (getDefaultMiddleWare) => getDefaultMiddleWare().prepend(listenerMiddleware.middleware)
});

export type RootState = ReturnType<typeof store.getState>
export type AppDispatch = typeof store.dispatch
export default store;