import {createSlice} from '@reduxjs/toolkit';

interface InitialState {
    isConferenceCreationModalOpen: boolean;
    isConferenceEditingModalOpen: boolean;
    selectedDate?: string;
    selectedMonth?: number;
}

const initialState: InitialState = {
    isConferenceCreationModalOpen: false,
    isConferenceEditingModalOpen: false,
    selectedDate: undefined,
    selectedMonth: undefined,
}

const userInterfaceSlice = createSlice({
    name:'userInterface',
    initialState,
    reducers:{
        setIsConferenceCreationModalOpen: (state, action) => {
            state.isConferenceCreationModalOpen = action.payload;
        },
        setIsConferenceEditingModalOpen: (state, action) => {
            state.isConferenceEditingModalOpen = action.payload;
        },
        setSelectedDate: (state, action) => {
            state.selectedDate = action.payload;
        },
        setSelectedMonth: (state, action) => {
            state.selectedMonth = action.payload;
        }
    }
});

export const {setIsConferenceCreationModalOpen, setSelectedDate, setSelectedMonth, setIsConferenceEditingModalOpen} = userInterfaceSlice.actions;
export default userInterfaceSlice.reducer;