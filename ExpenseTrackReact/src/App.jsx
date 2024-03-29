import { useState } from 'react'
import { Routes } from 'react-router-dom'
import { Route } from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SignUpPage from './Pages/SignUpPage.jsx'
import { createTheme, ThemeProvider } from '@mui/material/styles'
import CssBaseline from '@mui/material/CssBaseline'
import ForgotPasswordPage from './Pages/ForgotPasswordPage.jsx'
import SignInPage from './Pages/SignInPage.jsx'
import { HomePage } from './Pages/HomePage.jsx'
import ViewExpensePage from './Pages/ViewExpensePage.jsx'
import StatisticsPage from './Pages/StatisticsPage.jsx'
import UpdateDetailsPage from './Pages/UpdateDetailsPage.jsx'

import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'
import { Container } from '@mui/material'



function App() {
  // pass into apges whjich need authentication
  const [bearer, setBearer] = useState("")
  console.log(AdapterDayjs)

  const defaultTheme = createTheme({
    palette: {
      background: {
        default : "#e3f2fd",
      } 
    },
  });

  return (
    <>
    
      <ThemeProvider theme={defaultTheme} >
      <CssBaseline/>
      <LocalizationProvider dateAdapter={AdapterDayjs} >
      
     

        <Routes>
          {/* <Route path="/" element ={<SignInPage />}/> */}
          {bearer=="" && <Route path="/" element ={<SignInPage bearer={[bearer, setBearer]}/>}/>}
          {bearer!="" && <Route path="/homepage" element ={<HomePage bearer={bearer}/>}/>}
          <Route path="/signup" element ={<SignUpPage />}/>
          <Route path="/forgotpassword" element ={<ForgotPasswordPage />}/>
          <Route path="/viewexpense" element ={<ViewExpensePage bearer={bearer}/>}/>
          <Route path="/viewstatistics" element ={<StatisticsPage bearer={bearer}/>}/>
          <Route path="/updatedetails" element ={<UpdateDetailsPage bearer={bearer}/>}/>
          <Route path="*"  element ={<SignInPage bearer={[bearer, setBearer]}/>}/>
        </Routes>
        
      </LocalizationProvider>
     
      </ThemeProvider>
     
    </>
  )
}

export default App
