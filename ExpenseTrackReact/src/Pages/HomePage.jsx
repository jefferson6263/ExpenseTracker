import React from 'react'
import { useEffect } from 'react'
import axios from 'axios'
import { useState } from 'react'
import { LocalizationProvider } from '@mui/x-date-pickers/LocalizationProvider';
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs';

import BasicDateCalendar from '../Components/Calender';
import dayjs from 'dayjs';
import { DatePicker } from '@mui/lab';
import Calendar from '../Components/Calender';
import { MobileDatePicker } from "@mui/x-date-pickers/MobileDatePicker";
import { DateCalendar } from '@mui/x-date-pickers';
import { Box, IconButton, List } from '@mui/material';

import { AppBar } from '@mui/material';
import { Toolbar } from '@mui/material';
import { Typography } from '@mui/material';
import { Badge } from '@mui/material';

import { Drawer } from '@mui/material';
import { Divider } from '@mui/material';
import { Container } from '@mui/material';
import { Grid } from '@mui/material';
import { Paper } from '@mui/material';
import TemporaryDrawer from '../Components/VerticalDashBoard';
import StaticDatePickerLandscape from '../Components/Calender';
import Button from '@mui/material/Button';
import DashBoard from '../Components/VerticalDashBoard';
import ExpenseCard from '../Components/ExpenseCard';
import AllExpenses from '../Components/AllExpenses';

export const HomePage = (props) => {
    const {bearer} = props

    const [firstName, setfirstName] = useState("");

   
    useEffect(()=>{
        const requestOptions = {
            headers:{
                Authorization: "Bearer " + bearer
            }
        }

        axios.get("http://localhost:8088/expensetracker/getuserbytoken",requestOptions)
            .then(response=>{
                const {id, email, firstName, lastName, username} = response.data

                setfirstName(firstName)
            })


    },[]);
     

    return (
        <>
                
         
                {/* <DateCalendar style={{ margin: 'auto' }} /> */}
                <div style={{ display: 'flex', justifyContent: 'center', alignItems: 'center' }}>
                    
                <Box sx={{
      
                        marginTop: 8,
                        display: 'flex',
                        flexDirection: 'column',

                        
                        alignItems: 'center',
                        backgroundColor: 'white',
                        borderRadius: 20,
                        padding: 5,
                        boxShadow: '8px 8px 25px rgba(0, 0, 0, 0.2)',
                        height: '450px',
                        width: '600px'
                        }}>
                        <StaticDatePickerLandscape bearer={bearer} />
                        </Box>
                </div>
                

        <DashBoard bearer={bearer}/>

      

        </>

    )
}


export default HomePage;
