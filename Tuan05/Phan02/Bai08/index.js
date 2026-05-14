const express = require('express');
const mysql = require('mysql2');

const app = express();
const PORT = 3000;

// Kết nối MySQL (dùng tên service "db")
const db = mysql.createConnection({
  host: 'db',
  user: 'user',
  password: 'password',
  database: 'mydb'
});

// Kết nối DB
db.connect((err) => {
  if (err) {
    console.error('MySQL connection failed:', err);
  } else {
    console.log('Connected to MySQL');
  }
});

// API test
app.get('/', (req, res) => {
  db.query('SELECT NOW() AS time', (err, results) => {
    if (err) return res.send('DB Error');
    res.send(`Database time: ${results[0].time}`);
  });
});

app.listen(PORT, () => {
  console.log(`Server running on port ${PORT}`);
});