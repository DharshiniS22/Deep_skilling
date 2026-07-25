const express = require('express');
const router = express.Router();
const Account = require('../models/Account');

// Create a new account
router.post('/', async (req, res) => {
  try {
    const { customerName, accountType, initialBalance } = req.body;
    const account = new Account({
      customerName,
      accountType,
      balance: initialBalance || 0
    });
    const savedAccount = await account.save();
    res.status(201).json(savedAccount);
  } catch (error) {
    res.status(400).json({ message: error.message });
  }
});

// Get account by ID
router.get('/:id', async (req, res) => {
  try {
    const account = await Account.findById(req.params.id);
    if (!account) {
      return res.status(404).json({ message: 'Account not found' });
    }
    res.json(account);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
});

// Update account balance
router.put('/:id/balance', async (req, res) => {
  try {
    const { amount, action } = req.body; // action can be 'deposit' or 'withdraw'
    const account = await Account.findById(req.params.id);
    
    if (!account) {
      return res.status(404).json({ message: 'Account not found' });
    }

    if (action === 'deposit') {
      account.balance += amount;
    } else if (action === 'withdraw') {
      if (account.balance < amount) {
        return res.status(400).json({ message: 'Insufficient funds' });
      }
      account.balance -= amount;
    } else {
      return res.status(400).json({ message: 'Invalid action. Use "deposit" or "withdraw"' });
    }

    const updatedAccount = await account.save();
    res.json(updatedAccount);
  } catch (error) {
    res.status(500).json({ message: error.message });
  }
});

module.exports = router;
