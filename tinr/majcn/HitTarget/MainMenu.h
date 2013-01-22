//
//  MainMenu.h
//  friHockey
//
//  Created by Matej Jan on 21.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import <Foundation/Foundation.h>

#import "Menu.h"
#import "TopScore.h"
#import "About.h"

@interface MainMenu : Menu {
	Image *background;
	
	Label *title, *subtitle;
	
	Button *singleplayer, *topScore, *about;
}

@end
