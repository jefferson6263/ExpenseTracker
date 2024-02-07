import { useState } from 'react'
import { Routes } from 'react-router-dom'
import { Route } from 'react-router-dom'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import SignIn from './Pages/SignInPage.jsx'
import SignUp from './Pages/SignUpPage.jsx'


function App() {


  return (
    <>
      <div>
     

        <Routes>
          <Route path="/" element ={<SignIn />}/>
          <Route path="/signup" element ={<SignUp />}/>
        </Routes>
      </div>
    </>
  )
}

export default App
