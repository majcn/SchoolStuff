//
//  Scene.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

#import "IScene.h"

@interface Scene : GameComponent <IScene> {
	// A list of items currently on the scene.
	NSMutableArray *items;
	
	// A list of adds and removes to be executed on the scene.
	NSMutableArray *actions;
	
	Event *itemAdded;
	Event *itemRemoved;
}

@end
