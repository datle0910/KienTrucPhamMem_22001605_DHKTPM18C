import { useEffect, useState } from "react";
import axios from "axios";
import API from "../api/api";
import {
  Card,
  CardContent,
  TextField,
  Button,
  Typography,
} from "@mui/material";

export default function AdminFood() {
  const [foods, setFoods] = useState([]);
  const [name, setName] = useState("");
  const [price, setPrice] = useState("");

  const loadFoods = () => {
    axios.get(`${API.FOOD}/foods`).then(res => setFoods(res.data));
  };

  useEffect(() => {
    loadFoods();
  }, []);

  const addFood = async () => {
    await axios.post(`${API.FOOD}/foods`, {
      name,
      price,
      description: ""
    });
    loadFoods();
  };

  const deleteFood = async (id) => {
    await axios.delete(`${API.FOOD}/foods/${id}`);
    loadFoods();
  };
  const updateFood = async (id) => {
    await axios.put(`${API.FOOD}/foods/${id}`, {
      name,
      price,
      description: ""
    });
    loadFoods();
  };

  return (
    <div style={{ padding: 20 }}>
      <Typography variant="h5">Admin - Manage Foods</Typography>

      <Card sx={{ p: 2, mt: 2 }}>
        <TextField label="Name" onChange={e => setName(e.target.value)} />
        <TextField label="Price" onChange={e => setPrice(e.target.value)} />

        <Button variant="contained" onClick={addFood}>
          Add Food
        </Button>
      </Card>

      {foods.map(f => (
        <Card key={f.id} sx={{ mt: 2, p: 2 }}>
          <Typography>{f.name} - {f.price}</Typography>

          <Button color="error" onClick={() => deleteFood(f.id)}>
            Delete
          </Button>
        </Card>
      ))}
    </div>
  );
}