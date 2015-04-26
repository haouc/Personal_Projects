//
//  InfoViewController.h
//  CarCola
//
//  Created by Hao Zhou on 3/11/15.
//  Copyright (c) 2015 Hao Zhou. All rights reserved.
//

#import <UIKit/UIKit.h>
#import <AVFoundation/AVFoundation.h>

@interface InfoViewController : UIViewController <AVAudioPlayerDelegate>
- (IBAction)backButton:(id)sender;
- (IBAction)rateButton:(id)sender;

- (IBAction)eraseButton:(id)sender;
- (IBAction)helpButton:(id)sender;

@property (weak, nonatomic) IBOutlet UIImageView *sweepImage;

@property (nonatomic) AVAudioPlayer * audio;
@property (weak, nonatomic) NSString *soundPath;




@end
