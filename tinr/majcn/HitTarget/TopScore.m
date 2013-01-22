//
//  Options.m
//  friHockey
//
//  Created by Matej Jan on 22.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import "TopScore.h"
#import "majcn.HitTarget.h"


@implementation TopScore

- (void) initialize {
	[super initialize];	
		
	// Text
	title = [[Label alloc] initWithFont:font text:[NSString stringWithFormat:@"Shots: %i", [hitTarget.progress getScore]] position:[Vector2 vectorWithX:160 y:10]];
	
	title.horizontalAlign = HorizontalAlignCenter;
	[scene addItem:title];
	
	[scene addItem:back];
}

- (void) dealloc
{
	[title release];
	[super dealloc];
}

@end
