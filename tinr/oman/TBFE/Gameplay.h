//
//  Gameplay.h
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "Retronator.TBFE.classes.h"

@interface Gameplay : GameComponent {
	Level *level;		
	Player *topPlayer;
	Renderer *renderer;	
	Player *computerPlayer;
}

- (id) initMultiplayerWithGame:(Game*)theGame levelClass:(Class)levelClass;

- (id) initSinglePlayerWithGame:(Game*)theGame levelClass:(Class)levelClass aiClass:(Class)aiClass;

@end
