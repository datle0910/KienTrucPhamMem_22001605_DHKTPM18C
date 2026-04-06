import axios from "axios";
import API from "../api/api";
import {
  Card,
  CardContent,
  Typography,
  Button,
  Select,
  MenuItem,
} from "@mui/material";
import { useState } from "react";

export default function Payment({ order }) {
  const [method, setMethod] = useState("COD");

  const pay = async () => {
    await axios.post(`${API.PAYMENT}/payments`, {
      orderId: order.id,
      method,
    });

    alert("🎉 Payment success!");
  };

  return (
    <div style={{ display: "flex", justifyContent: "center", marginTop: 80 }}>
      <Card sx={{ width: 400 }}>
        <CardContent>
          <Typography variant="h5">Payment</Typography>

          <Typography>Order: #{order.id}</Typography>
          <Typography>Total: {order.totalPrice} VND</Typography>

          <Select
            fullWidth
            value={method}
            onChange={(e) => setMethod(e.target.value)}
            sx={{ mt: 2 }}
          >
            <MenuItem value="COD">COD</MenuItem>
            <MenuItem value="BANKING">Banking</MenuItem>
          </Select>

          <Button fullWidth variant="contained" sx={{ mt: 2 }} onClick={pay}>
            Pay Now
          </Button>
        </CardContent>
      </Card>
    </div>
  );
}