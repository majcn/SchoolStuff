//
//  GameHud.h
//  HitTarget
//
//  Created by Gregor Majcen on 1/4/11.
//  Copyright 2011 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

@interface GameHud : GameComponent
{
	Scene *scene;
	
	Label *plyerShots;
}

@property (nonatomic, readonly) Scene *scene;

- (void) changePlayerShots:(int)value;

@end
