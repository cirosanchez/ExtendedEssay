from javascript import require
import random
import time

mineflayer = require('mineflayer')


def chat(bot):
    jokes = [
        "What do you call a lazy kangaroo? A pouch potato.",
        "Why did the chicken cross the road? To get to the other side.",
        "I tried starting a hot air balloon business, but it never took off.",
        "Why did the scarecrow love his job? He had outstanding performance in his field.",
        "What do you call a fish with no eyes? Fsh.",
        "I tried starting a joke club, but it fell flat.",
        "Why did the cookie go to the doctor? Because it felt crummy.",
        "What do you call a sleeping bull? A bulldozer.",
        "I tried to tell a joke to my dog, but he just looked at me with a puzzled paw.",
        "Why did the tomato turn red? Because it saw a salad dressing.",
        "What do you call a lazy kangaroo? A pouch potato.",
        "Why did the chicken cross the road? To get to the other side.",
        "I tried starting a hot air balloon business, but it never took off.",
        "Why did the scarecrow love his job? He had outstanding performance in his field.",
        "What do you call a fish with no eyes? Fsh.",
        "I tried starting a joke club, but it fell flat.",
        "Why did the cookie go to the doctor? Because it felt crummy.",
        "What do you call a sleeping bull? A bulldozer.",
        "I tried to tell a joke to my dog, but he just looked at me with a puzzled paw.",
        "Why did the tomato turn red? Because it saw a salad dressing.",
        "What do you call a lazy kangaroo? A pouch potato.",
        "Why did the chicken cross the road? To get to the other side.",
        "I tried starting a hot air balloon business, but it never took off.",
        "Why did the scarecrow love his job? He had outstanding performance in his field.",
        "What do you call a fish with no eyes? Fsh.",
        "I tried starting a joke club, but it fell flat.",
        "Why did the cookie go to the doctor? Because it felt crummy.",
        "What do you call a sleeping bull? A bulldozer.",
        "I tried to tell a joke to my dog, but he just looked at me with a puzzled paw.",
        "Why did the tomato turn red? Because it saw a salad dressing.",
        "What do you call a lazy kangaroo? A pouch potato.",
        "Why did the chicken cross the road? To get to the other side.",
        "I tried starting a hot air balloon business, but it never took off.",
        "Why did the scarecrow love his job? He had outstanding performance in his field.",
        "What do you call a fish with no eyes? Fsh.",
        "I tried starting a joke club, but it fell flat.",
        "Why did the cookie go to the doctor? Because it felt crummy.",
        "What do you call a sleeping bull? A bulldozer.",
        "I tried to tell a joke to my dog, but he just looked at me with a puzzled paw.",
        "Why did the tomato turn red? Because it saw a salad dressing.",
        "What do you call a lazy kangaroo? A pouch potato.",
        "Why did the chicken cross the road? To get to the other side.",
        "I tried starting a hot air balloon business, but it never took off.",
        "Why did the scarecrow love his job? He had outstanding performance in his field.",
        "What do you call a fish with no eyes? Fsh.",
        "I tried starting a joke club, but it fell flat.",
        "Why did the cookie go to the doctor? Because it felt crummy.",
        "What do you call a sleeping bull? A bulldozer.",
        "I tried to tell a joke to my dog, but he just looked at me with a puzzled paw.",
        "Why did the tomato turn red? Because it saw a salad dressing.",
    ]
    num = random.randint(0, len(jokes)-1)
    joke = jokes[num]

    bot.chat(joke)


def jump(bot):
    bot.setControlState('jump', True)
    bot.setControlState('jump', False)


def sneak(bot):
    bot.setControlState('sneak', True)
    bot.setControlState('sneak', False)


random_names = [
    "Alice", "Bob", "Charlie", "David", "Eve", "Frank", "Grace", "Harry",
    "Ivy", "Jack", "Kate", "Leo", "Mia", "Noah", "Olivia", "Paul",
    "Quinn", "Riley", "Sarah", "Thomas", "Ursula", "Vincent", "William", "Xavier",
    "Yvette", "Zachary", "Abigail", "Benjamin", "Chloe", "Daniel", "Emily", "Finn",
    "Gabriel", "Hannah", "Isaac", "Jacob", "Katherine", "Liam", "Madison", "Nathan",
    "Olivia", "Parker", "Quinn", "Riley", "Sarah", "Thomas", "Ursula", "Vincent",
    "William", "Xavier", "Yvette", "Zachary", "Abigail", "Benjamin", "Chloe",
    "Daniel", "Emily", "Finn", "Gabriel", "Hannah", "Isaac", "Jacob", "Katherine",
    "Liam", "Madison", "Nathan", "Olivia", "Parker", "Quinn", "Riley", "Sarah",
    "Thomas", "Ursula", "Vincent", "William", "Xavier", "Yvette", "Zachary"
]

bots = []

for i in range(0, 31):
    time.sleep(4.5)
    tempBot = mineflayer.createBot({
        "host": "localhost",
        "port": "25565",
        "username": random_names[i]
    })
    bots.append(tempBot)
    print(random_names[i], " spawned!")

enabled = True

while enabled:
    time.sleep(1)
    for i in range(0, 6):
        bot = random.choice(bots)
        decision = random.randint(1, 3)
        print(bot.username+" is doing smth")
        if decision == 1:
            chat(bot)
        elif decision == 2:
            jump(bot)
        elif decision == 3:
            sneak(bot)
