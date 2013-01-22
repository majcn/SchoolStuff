//
//  Physics.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

@interface Physics : GameComponent
{
	Level *level;
}

- (id) initWithGame:(Game*)theGame level:(Level*)theLevel;

@end
