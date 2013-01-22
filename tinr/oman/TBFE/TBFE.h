//
//  TBFE.h
//  TBFE
//
//  Created by Marko Oman on 10/21/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Retronator.Xni.Framework.h"
#import "Retronator.Xni.Framework.Graphics.h"
#import "Retronator.Xni.Framework.Content.h"
#import "Retronator.Xni.Framework.Input.Touch.h"

#import "Retronator.TBFE.classes.h"

@interface TBFE : Game {
	GraphicsDeviceManager *graphics;
	
	// Gameplay
	Gameplay *currentGameplay;
	
	// Levels
	NSMutableArray *levelClasses;
}

- (void) loadMultiplayerLevel:(Class) levelClass;

@end
