//
//  Gameplay.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"
#import "GameState.h"
#import "Retronator.Xni.Framework.Audio.h"

@interface Gameplay : GameState
{	
	SoundEffect *music;
	Level *level;
	GameHud *hud;
	Player *player;
	Physics *physics;
	Renderer *renderer;
	GuiRenderer *hudRenderer;
	
	int shots;
}

@property (nonatomic, readonly) Level *level;

- (void) reset;

@end
