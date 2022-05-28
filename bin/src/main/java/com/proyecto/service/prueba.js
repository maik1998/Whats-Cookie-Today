import React from 'react'
import ListInventoryComponent from '../../components/ListInventoryComponent';
import FormInventoryComponent from '../../components/FormInventoryComponent';
import DialogComponent from '../../components/DialogComponent';
import { Container } from "@mui/material";
import Typography from '@mui/material/Typography';
import { useNavigate, useParams } from "react-router-dom";

export default function ManagementInventory() {
  const [open, setOpen] = React.useState(false);
  const navigate = useNavigate();
  const params = useParams();

  const handleOpen = () => {
    navigate('formIngredient/add');
    setOpen(true);
  };

  const handleOpenEdit = (id) => {
    navigate('edit/'+id);
    setOpen(true);
  };

  const handleClose = () => {
    setOpen(false);
    navigate('/inventarios');
  };

  return (
    <Container>
        <br/>
        <Typography variant="h6" align="center"> Inventarios</Typography>
          <DialogComponent nameButton="Agregar Inventarios" params={params} nameAdd="Registrar Ingrediente" nameEdit="Modificar Ingrediente" open={open} handleClose={handleClose} handleOpen={handleOpen}>
              <FormInventoryComponent handleClose={handleClose} params={params}/>
          </DialogComponent>
        <ListInventoryComponent params={params} handleOpen={handleOpen} handleOpenEdit={handleOpenEdit}></ListInventoryComponent>
    </Container>
  )
}
