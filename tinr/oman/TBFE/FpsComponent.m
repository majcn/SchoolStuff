//
//  FpsComponent.m
//  TBFE
//
//  Created by Marko Oman on 11/6/10.
//  Copyright 2010 __MyCompanyName__. All rights reserved.
//

#import "FpsComponent.h"


@implementation FpsComponent

- (void) updateWithGameTime:(GameTime *)gameTime {
	fpsCounter++;
	secondCounter += gameTime.elapsedGameTime;
	if (secondCounter > 1) {
		NSLog(@"FPS: %i", fpsCounter);
		fpsCounter = 0;
		secondCounter = 0;
	}
}

@end