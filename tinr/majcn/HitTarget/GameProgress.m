//
//  GameProgress.m
//  friHockey
//
//  Created by Matej Jan on 22.12.10.
//  Copyright 2010 Retronator. All rights reserved.
//

#import "GameProgress.h"

#import "majcn.HitTarget.h"

@implementation GameProgress

- (id) init
{
	self = [super init];
	if (self != nil) {
		// Unlock first opponent and level type.
		highScore = 0;
	}
	return self;
}

- (id) initWithCoder:(NSCoder *)aDecoder {
	self = [super init];
	if (self != nil)
	{
		highScore = [aDecoder decodeIntForKey:[NSString stringWithFormat:@"highScore"]];
	}	
	return self;
}

- (void) encodeWithCoder:(NSCoder *)aCoder
{
	[aCoder encodeInt:highScore forKey:[NSString stringWithFormat:@"highScore"]];
}

+ (GameProgress *) loadProgress {
	// Load game progress from file.
	GameProgress *progress = nil;
	
	NSString *rootPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
	NSString *archivePath = [rootPath stringByAppendingPathComponent:@"HitTargetSave"];		
	progress = [NSKeyedUnarchiver unarchiveObjectWithFile:archivePath];
	
	// If there is no progress file, create a fresh instance.
	if (!progress)
	{
		progress = [[[GameProgress alloc] init] autorelease];
	}
	
	NSLog(@"Progress retain count:", [progress retainCount]);
	
	return progress;
}

+ (void) deleteProgress {
	// Delete game progress file.
	NSString *rootPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
	NSString *archivePath = [rootPath stringByAppendingPathComponent:@"HitTargetSave"];
	NSError *error;
	[[NSFileManager defaultManager] removeItemAtPath:archivePath error:&error];
}

- (void) saveProgress {
	// Save game progress to file.
	NSString *rootPath = [NSSearchPathForDirectoriesInDomains(NSDocumentDirectory, NSUserDomainMask, YES) objectAtIndex:0];
	NSString *archivePath = [rootPath stringByAppendingPathComponent:@"HitTargetSave"];
	[NSKeyedArchiver archiveRootObject:self toFile:archivePath];
}

- (void) incScore
{
	highScore++;
	[self saveProgress];
}

- (int) getScore
{
	return highScore;
}

@end