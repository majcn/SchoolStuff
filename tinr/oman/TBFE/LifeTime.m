//
//  LifeTime.m
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "LifeTime.h"

@implementation Lifetime

- (id) initWithStart:(NSTimeInterval)theStart duration:(NSTimeInterval)theDuration
{
	self = [super init];
	if (self != nil) {
		start = theStart;
		duration = theDuration;
	}
	return self;
}

@synthesize start, duration, progress;

- (float) percentage {
	return (progress/duration);
}

- (BOOL) isAlive {
	return progress < duration;
}

- (void) updateWithGameTime:(GameTime *)gameTime {
	if (self.isAlive) {
		progress += gameTime.elapsedGameTime;
		if (!self.isAlive) {
			progress = duration;
		}
	}
}

@end

