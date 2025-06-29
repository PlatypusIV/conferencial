import { configureStore } from "@reduxjs/toolkit";
import conferenceReducer from "./conferenceActions";
import participantReducer from "./participantActions";
import userInterfaceReducer from "./userInterfaceActions";
import roomReducer from "./roomActions";

const store = configureStore({
  reducer: {
    conference: conferenceReducer,
    participant: participantReducer,
    userInterface: userInterfaceReducer,
    room: roomReducer,
  },
});

export type RootState = ReturnType<typeof store.getState>;
export type AppDispatch = typeof store.dispatch;
export default store;
