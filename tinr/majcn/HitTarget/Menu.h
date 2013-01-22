//
//  Menu.h
//  friHockey
//
//  Created by Matej Jan on 22.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import "Button.h"
#import "GameState.h"
#import "MirageEnums.h"

@interface Menu : GameState {
	Scene *scene;	
	GuiRenderer *renderer;
	
	SpriteFont *font;
	
	Button *back;
}

@end
