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

import { LocalizationProvider } from '@mui/x-date-pickers'
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'



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

   
      <LocalizationProvider dateAdapter={AdapterDayjs} >
      <ThemeProvider theme={defaultTheme} >
      <CssBaseline />

        <Routes>
          {/* <Route path="/" element ={<SignInPage />}/> */}
          {bearer=="" && <Route path="/" element ={<SignInPage bearer={[bearer, setBearer]}/>}/>}
          {bearer!="" && <Route path="/homepage" element ={<HomePage bearer={bearer}/>}/>}
          <Route path="/signup" element ={<SignUpPage />}/>
          <Route path="/forgotpassword" element ={<ForgotPasswordPage />}/>
          <Route path="*"  element ={<SignInPage bearer={[bearer, setBearer]}/>}/>
        </Routes>

      </ThemeProvider>
      </LocalizationProvider>
    

    </>
  )
}

export default App
