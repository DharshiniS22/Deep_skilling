require('dotenv').config();
const express = require('express');
const mongoose = require('mongoose');
const loanRoutes = require('./routes/loanRoutes');

const app = express();
const PORT = process.env.PORT || 3002;

// Middleware
app.use(express.json());

// Routes
app.use('/loans', loanRoutes);

// Health check endpoint
app.get('/health', (req, res) => res.send('Loan Service is running'));

// Database connection
const MONGODB_URI = process.env.MONGODB_URI || 'mongodb://localhost:27017/bank-loan-db';
mongoose.connect(MONGODB_URI)
  .then(() => {
    console.log('Connected to MongoDB for Loan Service');
    app.listen(PORT, () => {
      console.log(`Loan Service listening on port ${PORT}`);
    });
  })
  .catch(err => {
    console.error('Failed to connect to MongoDB:', err);
  });
