//
//  Networking.h
//  Google_News
//
//  Created by Hao Zhou on 2/15/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <Foundation/Foundation.h>
#import <UIKit/UIKit.h>

@interface SharedNetworking : NSObject

+ (id) sharedNetworking;
-(id) init;
- (void) getFeedForURL:(NSString *)url success:(void (^)(NSMutableDictionary *dictionary, NSError *error))successCompletion failure:(void (^)(void))failureCompletion;

- (bool) networkIsActive;
@end
