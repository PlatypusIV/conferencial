import {createSlice} from '@reduxjs/toolkit';

interface InitialState {
    isConferenceFormOpen: boolean;
    selectedDate?: string;
    isError?: boolean;
    messageText?: string;
}

const initialState: InitialState = {
    isConferenceFormOpen: false,
    selectedDate: undefined,
    isError: false,
    messageText: "",
}

const userInterfaceSlice = createSlice({
    name:'userInterface',
    initialState,
    reducers:{
        setConferenceFormIsOpen: (state, action) => {
            state.isConferenceFormOpen = action.payload;
        },
        setSelectedDate: (state, action) => {
            state.selectedDate = action.payload;
        },
        setIsError: (state, action) => {
            state.isError = action.payload;
        },
        setMessageText: (state, action) => {
            state.messageText = action.payload;
        }
    }
});

export const {setConferenceFormIsOpen, setSelectedDate, setIsError, setMessageText} = userInterfaceSlice.actions;
export default userInterfaceSlice.reducer;