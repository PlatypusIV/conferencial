import {createSlice} from '@reduxjs/toolkit';

const initialState = {
    
}

const userInterfaceSlice = createSlice({
    name:'userInterface',
    initialState,
    reducers:{
        setConferenceFormIsOpen: (state, action) => {
            state = action.payload;
        }
    }
});

export const {setConferenceFormIsOpen} = userInterfaceSlice.actions;
export default userInterfaceSlice.reducer;