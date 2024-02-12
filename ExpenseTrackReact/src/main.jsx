import React from 'react'
import ReactDOM from 'react-dom/client'
import App from './App.jsx'
import './index.css'
import { BrowserRouter } from 'react-router-dom'

import { LocalizationProvider } from "@mui/x-date-pickers";
import { AdapterDayjs } from '@mui/x-date-pickers/AdapterDayjs'


ReactDOM.createRoot(document.getElementById('root')).render(
  

  <React.StrictMode>
      
        <BrowserRouter>
          <App />
        </BrowserRouter>
   
  </React.StrictMode>, 
  
)
