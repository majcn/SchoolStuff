//
//  Level.h
//  HitTarget
//
//  Created by Gregor Majcen on 10/24/10.
//  Copyright 2010 majcn. All rights reserved.
//

#import <Foundation/Foundation.h>
#import "majcn.HitTarget.classes.h"

@interface Level : GameComponent
{
	Scene *scene;
	Archer *archer;
	Arrow *arrow;
	Target *target;
	Fire *fire;
}

@property (nonatomic, readonly) Scene *scene;
@property (nonatomic, readonly) Archer *archer;
@property (nonatomic, readonly) Arrow *arrow;
@property (nonatomic, readonly) Fire *fire;
@property (nonatomic, readonly) Target *target;

- (void) reset;

@end
