//
//  Options.m
//  friHockey
//
//  Created by Matej Jan on 22.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import "About.h"
#import "majcn.HitTarget.h"


@implementation About

- (void) initialize {
	[super initialize];	
		
	// Text
	title = [[Label alloc] initWithFont:font text:@""
			 "created by\n"
			 "Gregor Majcen\n"
			 "Published by GameTeam, Fri\n" position:[Vector2 vectorWithX:160 y:10]];
	
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
