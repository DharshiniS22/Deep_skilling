const express = require('express');
const router = express.Router();
const Loan = require('../models/Loan');

// Apply for a new loan
router.post('/', async (req, res) => {
  try {
    const { accountId, amount, interestRate } = req.body;
    const loan = new Loan({
      accountId,
      amount,
      interestRate
    });
    const savedLoan = await loan.save();
    res.status(201).json(savedLoan);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
});

// Get loan by ID
router.get('/:id', async (req, res) => {
  try {
    const loan = await Loan.findById(req.params.id);
    if (!loan) {
      return res.status(404).json({ message: 'Loan not found' });
    }
    res.json(loan);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
});

// Get all loans for an account
router.get('/account/:accountId', async (req, res) => {
  try {
    const loans = await Loan.find({ accountId: req.params.accountId });
    res.json(loans);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
});

// Update loan status
router.put('/:id/status', async (req, res) => {
  try {
    const { status } = req.body;
    
    if (!['Pending', 'Approved', 'Rejected'].includes(status)) {
      return res.status(400).json({ message: 'Invalid status' });
    }

    const loan = await Loan.findById(req.params.id);
    if (!loan) {
      return res.status(404).json({ message: 'Loan not found' });
    }

    loan.status = status;
    const updatedLoan = await loan.save();
    res.json(updatedLoan);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
});

module.exports = router;
