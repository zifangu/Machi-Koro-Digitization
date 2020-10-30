# Machi WoCo User Guide
This is the user guide for playing Machi Woco.
## Contents
  * [Phase 1](#phase-1)
    + [Game Objective](#game-objective)
    + [Game Play](#game-play)
        - [Start Game](#start-game)
        - [Each Round](#each-round)
             - [Dice Roll](#dice-roll)
             - [Menu](#menu)        
             - [View](#view)    
             - [Purchase](#purchase)
             - [Construct](#construct)
             - [End Turn](#end-turn)
        - [AI-strategy](#ai-strategy)

    + [Winner](#winner)
  * [Phase 2](#phase-2)
    + [Game Objective P2](#game-objective-P2)
    + [Game Play P2](#game-play-P2)
        - [Start Game](#start-game-P2)
        - [Each Round](#each-round-P2)
        - [Gameplay Differences P2](#gameplay-differences-P2)
            - [Dice Roll P2](#dice-roll-P2)
            - [Purchase P2](#purchase-P2)
    + [Winner P2](#winner-P2)


  
# Phase 1

## Game Objective

For this version of the game, the objective is to be the first player to build City Hall.

## Game Play

### Start Game
In this version of the game, there will be two players: player 1 and player 2.
When a new game starts, player 1 will go first.

### Each Round
At the beginning of each player's turn, you will first see a display of the current game state similar to this:

    ******************************************
                      MARKET                  
    ------------------------------------------
    Wheat Field        BW (1)  [1]      #6
    Ranch              BC (1)  [2]      #6
    Forest             BG (3)  [5]      #6

                 Player 1* [YOU]              
    ------------------------------------------
                    (4 coins)                 
    Wheat Field        BW (1)  [1]      #1
    ..........................................
    City Hall          NT (7)  [ ]
    
                     Player 2                 
    ------------------------------------------
                    (4 coins)                 
    Wheat Field        BW (1)  [1]      #1
    ..........................................
    City Hall          NT (7)  [ ]
    
    ******************************************
    
Market contains the establishments available to all players to purchase.
Each market entry contains these information(in such order): 
* name of the establishment
* two characters representing color and icon
* the cost in parentheses
* the activation range
* the number available, starting with a single space and the hashtag/pound symbol

 
Each player entry also contains similar information about the establishments and landmarks with the exception that in landmarks `[ ]` indicates whether that landmark has been constructed or not as opposed to the activation range in establishments, separated by `..........................................`
#### Dice Roll
At this point of the game, a player can only roll one dice .
That dice will be rolled immediately and automatically following the display of the current game state.

For example, `Player 1 rolled [5] = 5` means player 1 rolled a 5.


#### Menu
After the dice rolls, you will be prompted to purchase or construct items as you wish. A menu similar to this will be displayed to you.
    
    Player 1 would you like to purchase an 
    establishment or construct a landmark? (5
     coins
    (To view details of an item, type 'view'  
    followed by the item number. For example, 
    to view item 6, type 'view 6'.)           
    ==========================================
    ---------        PURCHASE        ---------
     1. Wheat Field        BW (1)  [1]      #6
     2. Ranch              BC (1)  [2]      #6
     3. Forest             BG (3)  [5]      #6
    ---------         CANCEL         ---------
    99. Do nothing                            
    ==========================================
    Choose a number to purchase or construct: 


You can check your account balance on the top of this prompt. 

#### View
If you are unsure about the effects of any establishments that you are able to purchase, you can type `view N` where N is the item number you see on the menu.

For example, in the menu displayed above, if you type `view 1` you can see detailed informaion about wheat field

    .-----------------------.
    | <B>      [1]      {W} |
    |      Wheat Field      |
    |                       |
    |  Get 1 coin from the  |
    |         bank.         |
    |    (anyone's turn)    |
    |                       |
    | (1)                   |
    |_______________________|
    
 #### Purchase
 If you desire to purchase an establishment, enter the item number corresponding to the item numbers displayed to you. For example, by typing `2`, you will purchase a ranch establishment. After which your turn ends.
 
 #### Construct
 If you choose not to purchase an establishment, you can construct a landmark if one is available in your menu.
 
 #### End turn
 Any invalid input (i.e. item numbers out of index or any phrases other than `view N`) will cause the console to prompt you again. Your turn will not end until a valid input is made.
 
 A turn ends after the player made a choice about the improvement on their landmarks and establishments.
 
 ### AI strategy
If a Player is an AI,  decisions will be made uniformly among the choices provided to it. The choices include all the establishments and landmarks it can purchase or construct, or do nothing each round.


 ### Winner
In this feature of the game, the winner will be player who constructed City Hall first. After which the game will immediately end.



# Phase 2

## Game Objective P2

For this version of the game, the objective is to be the first player to build City Hall and Train Station.

## Game Play P2

### Start Game P2
In this version of the game, there will be 2-3 players: 1 human player (player 1) and 1-2 AI player(s).
When a new game starts, player 1 will go first.

### Each Round P2
At the beginning of each player's turn, you will first see a display of the current game state similar to this:

    ******************************************
                      MARKET                  
    ------------------------------------------
    Wheat Field        BW (1)  [1]      #6
    Ranch              BC (1)  [2]      #6
    Bakery             GB (1)  [2-3]    #6
    Convenience Store  GB (2)  [4]      #6
    Forest             BG (3)  [5]      #6
    Mine               BG (6)  [9]      #6
    Apple Orchard      BW (3)  [10]     #6
    
                 Player 1* [YOU]              
    ------------------------------------------
                    (3 coins)                 
    Wheat Field        BW (1)  [1]      #1
    Bakery             GB (1)  [2-3]    #1
    ..........................................
    City Hall          NT (7)  [ ]
    Train Station      NT (4)  [ ]
    
                     Player 2                 
    ------------------------------------------
                    (3 coins)                 
    Wheat Field        BW (1)  [1]      #1
    Bakery             GB (1)  [2-3]    #1
    ..........................................
    City Hall          NT (7)  [ ]
    Train Station      NT (4)  [ ]
    
                     Player 3                 
    ------------------------------------------
                    (3 coins)                 
    Wheat Field        BW (1)  [1]      #1
    Bakery             GB (1)  [2-3]    #1
    ..........................................
    City Hall          NT (7)  [ ]
    Train Station      NT (4)  [ ]
    
    ******************************************
    
The Establishments Bakery, Convenience Store, Mine, and Apple Orchard have been added.

### Gameplay Differences P2

#### Dice Roll P2
At this point of the game, a player can roll two die if they have constructed the Train Station landmark.
Otherwise, only one die will be rolled.
These die will be rolled immediately and automatically following the display of the current game state.

Once the AI constructs the Train Station, on subsequent moves, it will randomly select 1 or 2 die to roll. 

For example, `Player 1 rolled [5] = 5` means player 1 rolled a 5.
`Player 2 rolled [5][6] = 11` means player 2 rolled an 11.

 #### Purchase P2
If a Player is an AI, purchasing decisions will be made at random. If an AI player has the funds necessary a purchase can be made, but they also have the choice to skip and move on to the next player.
 
 ### Winner P2
In this feature of the game, the winner will be the player who constructed City Hall and Train Station first. After which the game will immediately end.


