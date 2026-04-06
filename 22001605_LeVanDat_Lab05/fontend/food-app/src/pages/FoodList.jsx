import { useEffect, useState } from "react";
import axios from "axios";
import API from "../api/api";
import {
  Card,
  CardContent,
  Typography,
  Button,
  Grid,
  CardMedia,
} from "@mui/material";

export default function FoodList({ cart, setCart }) {
  const [foods, setFoods] = useState([]);

  useEffect(() => {
    axios.get(`${API.FOOD}/foods`).then((res) => setFoods(res.data));
  }, []);

  const addToCart = (food) => {
    setCart([...cart, food]);
  };

  return (
    <Grid container spacing={2}>
      {foods.map((f) => (
        <Grid item xs={4} key={f.id}>
          <Card
            sx={{
              transition: "0.3s",
              "&:hover": { transform: "scale(1.05)" },
            }}
          >
            <CardMedia
              component="img"
              height="140"
              image="https://source.unsplash.com/300x200/?food"
            />

            <CardContent>
              <Typography variant="h6">{f.name}</Typography>
              <Typography color="text.secondary">
                {f.price} VND
              </Typography>

              <Button
                fullWidth
                variant="contained"
                sx={{ mt: 1 }}
                onClick={() => addToCart(f)}
              >
                Add to Cart
              </Button>
            </CardContent>
          </Card>
        </Grid>
      ))}
    </Grid>
  );
}