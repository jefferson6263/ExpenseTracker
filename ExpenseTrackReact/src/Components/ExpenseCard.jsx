
import * as React from 'react';
import EditExpenseForm from './EditExpense';
import Card from '@mui/material/Card';
import CardHeader from '@mui/material/CardHeader';
import CardContent from '@mui/material/CardContent';
import CardActions from '@mui/material/CardActions';
import Collapse from '@mui/material/Collapse';
import IconButton from '@mui/material/IconButton';
import Typography from '@mui/material/Typography';
import ExpandMoreIcon from '@mui/icons-material/ExpandMore';
import DeleteIcon from '@mui/icons-material/Delete';
import ModeEditOutlinedIcon from '@mui/icons-material/ModeEditOutlined';
import axios from 'axios';
import { useState } from 'react';
import { useEffect } from 'react';
import { Chip } from '@mui/material';
import { Modal } from '@mui/material';
import { Box } from '@mui/material';
import { styled } from '@mui/material/styles';

const ExpandMore = styled((props) => {
  const { expand, ...other } = props;
  return <IconButton {...other} />;
})(({ theme, expand }) => ({
  transform: !expand ? 'rotate(0deg)' : 'rotate(180deg)',
  marginLeft: 'auto',
  transition: theme.transitions.create('transform', {
    duration: theme.transitions.duration.shortest,
  }),
}));


const style = {
  position: 'absolute',
  top: '50%',
  left: '50%',
  transform: 'translate(-50%, -50%)',
  width: 700,
  bgcolor: 'background.paper',
  boxShadow: 24,
  padding: 6,
  borderRadius: 8,
};



const ExpenseCard = (props) => {
  const [expanded, setExpanded] = React.useState(false);
  const [isDeleted, setIsDeleted] = useState(false);
  
  const [editMenu, setEditMenu] = useState(false);

  const [id, setId] = useState(0)
  const [name, setName] = useState("")
  const [amount, setAmount] = useState(0)
  const [description, setDescription] = useState("")
  const [categories, setCategories] = useState([])
  const [startDate, setStartDate] = React.useState(new Date())
  const [endDate, setEndDate] = React.useState(new Date())
  const [bearer, setBearer] = useState("")
  const [render, setRender] = useState(false)
  
  const date = (startDate === endDate) ? `${startDate}` : `${startDate} - ${endDate}`;

  useEffect(()=>{
    setId(props["id"])
    setName(props["name"])
    setAmount(props["amount"])
    setDescription(props["description"])
    setCategories(props["categories"])
    setStartDate(props["startDate"])
    setEndDate(props["endDate"])
    setBearer(props["bearer"])
  },[editMenu, ]);
 
 




  const handleExpandClick = () => {
    setExpanded(!expanded);
  };

  const deleteExpense = () => {
    axios.delete("http://localhost:8088/expensetracker/expenses/" + id)
        .then(() => {
          setIsDeleted(true); // Update state to trigger re-render
        })
   
    
  }

  const openEditExpense = () => {
    setEditMenu(true)
  }

  const closeEditExpense = () => {
    setEditMenu(false)
  }

  const t = () => {
    setRender(true)
  }

  if (isDeleted) {
    return null; 
  }



  return (
 
    <Card sx= {{ 
      maxWidth: 345, 
      borderRadius: 5, 
      boxShadow: '8px 8px 25px rgba(0, 0, 0, 0.2)',
      transition: 'transform 0.3s cubic-bezier(0.25, 0.46, 0.45, 0.94)',
      width: 350, 
    
      ':hover': {
      transform: 'scale(1.05)',
      boxShadow: 20, 
      
    }, }}>

      <CardHeader title={name}/>
      
      <CardContent>
        <Typography component="h1" variant="h5" fontFamily={"Lexend"} sx={{ fontSize: '24px' }}>
        Amount: ${amount}
          </Typography>
      </CardContent>
      <CardActions disableSpacing>

        <IconButton >
          <DeleteIcon onClick={deleteExpense} />
        </IconButton>

        <IconButton>
          <ModeEditOutlinedIcon onClick={openEditExpense}/>
        </IconButton>


        <Modal open={editMenu} onClose={closeEditExpense} onClick={t}>
          <Box sx={style}>

            <EditExpenseForm  
              id={id}
              name={name}
              amount={amount}
              description={description}
              startDate={startDate}
              endDate={endDate}
              categories={categories}
              bearer={bearer}
              
              />

          </Box>
          
        </Modal>
        <ExpandMore
          expand={expanded}
          onClick={handleExpandClick}
          aria-expanded={expanded}
          aria-label="show more"
        >
          <ExpandMoreIcon/>
        </ExpandMore>
      </CardActions>
      <Collapse in={expanded} timeout="auto" unmountOnExit>
        <CardContent>
        <Typography component="h1" variant="h5" fontFamily={"Lexend"} sx={{ fontSize: '24px', marginBottom: '10px' }}>
          Description
          </Typography>
          
          <Typography component="h1" variant="h5" fontFamily={"Lexend"} sx={{ fontSize: '16px', marginBottom: '10px' }}>
            {description}
          </Typography>

          <Typography component="h1" variant="h5" fontFamily={"Lexend"} sx={{ fontSize: '24px', marginBottom: '10px' }}>
            Date
          </Typography>


          <Typography component="h1" variant="h5" fontFamily={"Lexend"} sx={{ fontSize: '16px', marginBottom: '10px' }}>
            {date}
          </Typography>


          <Typography component="h1" variant="h5" fontFamily={"Lexend"} sx={{ fontSize: '24px', marginBottom: '10px' }}>
            Categories
          </Typography>

          {categories.map((category, index) => (
            <Chip 
              key={index} 
              label={category["name"]} 
              sx={{ mr: 1, mb: 1, p: '8px' }} // Add padding to each chip
            />
          ))}


        </CardContent>
      </Collapse>
    </Card>
  
  );
}

export default ExpenseCard