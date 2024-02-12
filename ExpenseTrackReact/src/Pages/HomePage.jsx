import React from 'react'
import { useEffect } from 'react'
import axios from 'axios'
import { useState } from 'react'
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';
import { DateCalendar } from '@mui/x-date-pickers/DateCalendar';
import BasicDateCalendar from '../Components/Calender';
import dayjs from 'dayjs';
import { DatePicker } from '@mui/lab';
import Calendar from '../Components/Calender';
import { MobileDatePicker } from "@mui/x-date-pickers/MobileDatePicker";

export const HomePage = (props) => {
    const {bearer} = props
    const [firstName, setfirstName] = useState("");
 
    
    useEffect(()=>{
        const requestOptions = {
            headers:{
                Authorization: bearer
            }
        }
        axios.get("http://localhost:8088/expensetracker/getuserbytoken",requestOptions)
            .then(response=>{console.log(response.data)
                const {id, email, firstName, lastName, username} = response.data
                console.log(id)
                setfirstName(firstName)
            })


    },[]);
     

    return (
        <>
            <h1>HomePage</h1>
            <h1>Welcome {firstName}</h1>
 

            {/* <DateCalendar/> */}
     
        </>

    )
}


export default HomePage;
