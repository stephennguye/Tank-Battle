# Tank Battle - 2D Turn-Based Game

This repository contains the **Tank Battle** project developed for the **Software Engineering** course.  
It is a 2D turn-based strategy game where two players control tanks and engage in combat on an **8x8 grid battlefield**, utilizing different types of projectiles and tactical movements to defeat their opponent.

---

## 🎮 Game Overview
- **Genre:** Turn-Based Strategy  
- **Board Size:** 8x8 Grid  
- **Players:** 2 (local multiplayer)  

Each player controls their tank(s), maneuvers across the battlefield, and selects from multiple projectile types to maximize damage and outplay their opponent.

---

## 🛠 Game Features

### 1. Game Board & Controls
- The battle takes place on an **8x8 grid**.  
- Player Controls:
  - **Player 1**: `W`, `A`, `S`, `D` to move, `Space` to shoot.  
  - **Player 2**: Arrow keys to move, `Enter` to shoot.  

### 2. Combat Mechanics
Each tank has the following attributes:
- **HP (Hit Points):** Represents tank health.  
- **Armor:** Reduces incoming damage (except armor-piercing projectiles).  
- **Damage:** Amount of damage per projectile.  
- **Price:** Used for balancing or purchasing tanks.

### 3. Weapon System
- **Standard Projectile** – Deals `(damage - armor)` damage.  
- **AP Projectile (Armor Piercing)** – Ignores armor and deals full damage.  
- **HE Projectile (High Explosive)** – Causes area-of-effect (AoE) damage, affecting multiple tiles.  

### 4. Objective
Outmaneuver and destroy the opponent's tanks using positioning, ammo selection, and strategy.

---

## 🛠 Tools & Technologies
- **Programming Language:** *(specify – C++, Java, or Python?)*  
- **Framework/Game Library:** *(e.g., SFML, Pygame, JavaFX – specify what you used)*  

---

## 🚀 Learning Outcome
This project demonstrates:
- Application of **Software Engineering principles** (modularity, design patterns, version control).  
- Implementation of **turn-based game mechanics** and **object-oriented design**.  
- Experience with **keyboard input handling**, **game logic**, and **real-time rendering**.
