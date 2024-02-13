import * as React from 'react';
import TextField from '@mui/material/TextField';
import { StaticDatePicker } from '@mui/x-date-pickers';
import { Zoom } from '@mui/material';
import { Box } from '@mui/material';


const StaticDatePickerLandscape = (props) => {
  const [value, setValue] = React.useState(new Date());

  return (
      <Zoom in={true}>
      <div style={{ width: 500, height: 400}}>

      <StaticDatePicker
        orientation="landscape"
        openTo="day"
        format="MM/dd/yyyy"


        onChange={(newValue) => {
          setValue(newValue);
        }}
        
        // renderInput={(params) => <TextField {...params} />}

        sx={{
       
          width: '100%', height: '100%'
        }}

        onAccept={() => {console.log(value)}}
      />
  
      </div>
      </Zoom>
  
  );
}

export default StaticDatePickerLandscape