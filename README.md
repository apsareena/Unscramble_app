Unscramble App
===================================

Unscramble is  a single player game app that displays scrambled words. To play the game, player has
to make a word using all the letters from the displayed scrambled word.


- Android architecture components are used in this app for better performance with less 
number of lines of codes and removes redundancy. 
- ViewModel is used for the data modelling to save the app state during configuration 
changes. 
- Data stored in the ViewModel is encapsulated with LiveData and added observer methods to 
observe changes in LiveData. 
- The observer methods get notified when the data changes, and update the scrambled word 
text view automatically. 
- Data binding is used and the binding expressions in the layout file will be triggered
when the LiveData changes. The score, word count and the unscrambled word text views in 
the app are updated automatically. 
![scrambled_word](https://user-images.githubusercontent.com/56432777/170812463-f9a8b0a1-12f5-4165-94f0-c8a47e7e6e45.jpg)
![wrong_answer](https://user-images.githubusercontent.com/56432777/170812470-5522567a-9bd4-4850-9acc-8ac70f240a22.jpg)
![answer](https://user-images.githubusercontent.com/56432777/170812473-251cb920-62a0-4cab-936b-f238280f72a5.jpg)
![final_output](https://user-images.githubusercontent.com/56432777/170812480-a046f51f-1ab6-403f-89ee-651829ae93e2.jpg)
