import React from 'react'
import { useEffect } from 'react'
import axios from 'axios'
import { useState } from 'react'
import { Box } from '@mui/material';
import StaticDatePickerLandscape from '../Components/Calender';
import DashBoard from '../Components/VerticalDashBoard';


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
