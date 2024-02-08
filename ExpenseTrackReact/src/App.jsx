import { useState } from 'react'
import { Routes } from 'react-router-dom'
import { Route } from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SignIn from './Pages/SignInPage.jsx'
import SignUp from './Pages/SignUpPage.jsx'
import { createTheme, ThemeProvider } from '@mui/material/styles';
import CssBaseline from '@mui/material/CssBaseline';
import ForgotPassword from './Pages/ForgotPasswordPage.jsx'

function App() {
  // pass into apges whjich need authentication
  const [bearer, setBearer] = useState("")

  const defaultTheme = createTheme({
    palette: {
      background: {
        default : "#e3f2fd",
      } 
    },
  });

  return (
    <>
      <div>
     
      <ThemeProvider theme={defaultTheme} >
      <CssBaseline />
        <Routes>
          <Route path="/" element ={<SignIn />}/>
          <Route path="/signup" element ={<SignUp />}/>
          <Route path="/forgotpassword" element ={<ForgotPassword />}/>
        </Routes>
      </ThemeProvider>

      </div>
    </>
  )
}

export default App
